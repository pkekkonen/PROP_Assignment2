(def <> not=)

(def entries '({:month 1 :val 12 :s1 true :s2 false}
               {:month 2 :val 3 :s1 false :s2 true}
               {:month 3 :val 32 :s1 true :s2 false}
               {:month 4 :val 18 :s1 true :s2 false}
               {:month 5 :val 32 :s1 false :s2 true}
               {:month 6 :val 62 :s1 false :s2 true}
               {:month 7 :val 12 :s1 false :s2 true}
               {:month 8 :val 142 :s1 true :s2 false}
               {:month 9 :val 52 :s1 true :s2 false}
               {:month 10 :val 18 :s1 true :s2 false}
               {:month 11 :val 23 :s1 false :s2 true}
               {:month 12 :val 56 :s1 false :s2 true}))

(def persons '({:id 1 :name "olle"} 
               {:id 2 :name "anna"} 
               {:id 3 :name "isak"} 
               {:id 4 :name "beatrice"}))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;




(defmacro select [[& wantedColumns] _ table _ [condColumn condOp condValue] _ orderArg]
  
  `(defn getColumns [wantedColumns table returnValue] 
    (if (empty? (rest table))
     (seq (conj returnValue (select-keys (first table) wantedColumns)))
     (recur wantedColumns (rest table) (conj returnValue (select-keys (first table) wantedColumns)))))
  
  `(getColumns [~@wantedColumns]
    (filter #(~condOp (~condColumn %) ~condValue) (sort-by ~orderArg ~table)) []))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(select [:id :name] from persons where [:id < 9] orderby :id) 

(select [:month] from entries where [:s1 = true] orderby :val)






