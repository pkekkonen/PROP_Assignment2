(ns safemacro.core
  (:gen-class))

(defmacro safe 
  ([expr]
   `(try 
     ~(expr)
     (catch Exception e (.getMessage e))))
  ([vector expr]
   ;; gör kontroll av längd på vector
   (try
     (let [var (first vector)
           value (first (rest vector))
           var value]
       expr))))
