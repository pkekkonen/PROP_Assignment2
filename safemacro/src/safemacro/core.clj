(ns safemacro.core
  (:gen-class))

(defmacro safe 
  ([expr]
   `(try 
     ~expr
     (catch Exception e# (.getMessage e#))))
  ([vector expr]
   ;; gör kontroll av längd på vector
   `(try
     ~(let [^Closable var (first vector)
            value (first (rest vector))
            var value]
        expr
        (. var close))
     (catch Exception e# (.getMessage e#)))))


(safe '([a 0] (/ 1 a)))
(safe '(/ 1 0))
(safe '(+ 1 2))
