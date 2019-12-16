(def <> not=)
(import java.util.Comparator)

(defmacro select [[& wantedColumns] _ table _ [condColumn condOp condValue] _ orderArg]

  (defn getColumns [table wantedColumns]
    (select-keys table wantedColumns))
  
  (defn filterCond [table condOp condColumn condValue]
    (filter #(condOp (condColumn %) condValue) table))
  
  (defn orderTable [table orderArg]
    (sort-by orderArg table))
  
  `(getColumns ~table ~wantedColumns))
  
   
  

(def entries [{:month 1 :val 12 :s1 true :s2 false}
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
              {:month 12 :val 56 :s1 false :s2 true}])

(def persons [{:id 1 :name "olle"} 
              {:id 2 :name "anna"} 
              {:id 3 :name "isak"} 
              {:id 4 :name "beatrice"}])

(macroexpand '(select [:id :name] from persons where [:id > 2] orderby :name)) 

(select [:id :name] from persons where [:id > 2] orderby :name) 

(select [:s2 :s1] from entries where [:month > 4] orderby :val)

(select [:s2] from entries where [:month > 4] orderby :val)

(macroexpand '(select [:s2 :s1] from entries where [:month > 4] orderby :val))

;; (filter (function) listan)
