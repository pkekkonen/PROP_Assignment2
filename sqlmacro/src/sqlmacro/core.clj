(def <> not=)

(def persons '({:id 1 :name "olle"} {:id 2 :name "anna"} {:id 3 :name "isak"} {:id 4 :name "beatrice"}))

(select [:id :name] from persons where [:id > 2] orderby :name) 

(defmacro select [[& wantedColumns] _ table _ [condColumn condOp condValue] _ orderArg]
  `(orderTable
    (filterCond
      ((getColumns ~table ~wantedColumns) ~condOp ~condColumn ~condValue))
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

(defn getColumns [table & wantedColumns]
  (for [x (range 0 (count table))] 
    (select-keys (get table x) wantedColumns)))

(getColumns entries :val :s2)

(defn filterCond [table condOp condColumn condValue]
  (filter #(condOp (condColumn %) condValue) table))

(filterCond (getColumns entries :val :s2) <> :val 3)

(defn orderTable [table orderArg]
  (sort-by orderArg  table))

(orderTable (filterCond (getColumns entries :val :s2) = :s2 true) :val)

;; (filter (function) listan)
