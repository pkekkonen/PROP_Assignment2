(def <> not=)

(def persons '({:id 1 :name "olle"} {:id 2 :name "anna"} {:id 3 :name "isak"} {:id 4 :name "beatrice"}))

(macroexpand '(select [:id :name] from persons where [:id > 2] orderby :name)) 

(select [:id :name] from persons where [:id > 2] orderby :name) 

(select [:s2 :s1] from entries where [:month > 4] orderby :val) 


(defmacro select [[& wantedColumns] _ table _ [condColumn condOp condValue] _ orderArg]
  `(orderTable
    (filterCond
      ((getColumns ~table ~wantedColumns)  ~condOp ~condColumn ~condValue))
    ~orderArg))

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

(defn getColumns 
  ([table wantedColumns]
   (if (empty? (rest table))
     (conj nil (select-keys (first table) wantedColumns))
     (getColumns table nil wantedColumns)))
  ([table result wantedColumns]
   (if (empty? (rest table))
     (conj result (select-keys (first table) wantedColumns))
     (getColumns (rest table) (conj result (select-keys (first table) wantedColumns)) wantedColumns))))

(defn getColumnsOldSolution [table & wantedColumns]
  (for [x (range 0 (count table))] 
    (select-keys (get table x) wantedColumns)))

(getColumns entries '(:val))

(defn filterCond [table condOp condColumn condValue]
  (filter #(condOp (condColumn %) condValue) table))

(filterCond (getColumns entries '(:val :s2)) <> :val 3)

(defn orderTable [table orderArg]
  (sort-by orderArg table))

(orderTable (filterCond (getColumns entries '(:val :s2)) = :s2 true) :val)

;; (filter (function) listan)
