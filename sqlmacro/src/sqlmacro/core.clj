
(defmacro select [wantedColumns _ table _ [condColumn condOp condValue] _ orderarg])


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

(defn getColumns [table]
  (for [x (range 0 (count table))] 
    (select-keys (get table x) [:val :s2])))

(getColumns entries)

(defn filterCond [table condOp condColumn condValue]
  (filter #(condOp (condColumn %) condValue) table))

(filterCond (getColumns entries) = :s2 true)

;; (filter (function) listan)
