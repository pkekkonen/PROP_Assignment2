(ns sqlmacro.core
  (:gen-class))


(defmacro select [[& wantedColumns] _ table _ [condColumn condOp condValue] _ orderArg]
  (defn getColumns [wantedColumns table returnValue] 
    (if (empty? (rest table))
      (seq (conj returnValue (select-keys (first table) wantedColumns)))
      (recur wantedColumns (rest table) (conj returnValue (select-keys (first table) wantedColumns)))))

  `(getColumns [~@wantedColumns]
    (filter #(~condOp (~condColumn %) ~condValue) (sort-by ~orderArg ~table)) []))







