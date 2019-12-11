(ns safemacro.core
  (:gen-class))

(import java.io.Closeable)

(defmacro safe 
  ([expr]
   `(try 
     (eval ~expr)
     (catch Exception e# (.getMessage e#))))
  ([vector expr]
   ;; gör kontroll av längd på vector
   `(try
     <<<<<<< HEAD
     (let [var# (first ~vector)
           value# (first (rest ~vector))
           var# value#] 
       (eval ~expr))
     (catch Exception e# (.getMessage e#)))))

(macroexpand '(safe [a 0] a))
(safe [a 0] (/ 1 a))
(safe [a 0] a)

(let [a 0] (/ 1 a))
(/ 1 3)
  
(defn let2 [vector]
  (def var (first vector))
  (def value (first (rest vector)))
  (let [var value]
    var))

(macroexpand '(let2 [a 0] a))
(let2 [1 2])
(let2 [:a 0])

(macroexpand '(safe [a 1] (/ 1 a)))
(safe [a 1] (/ 1 a))

(safe '(/ 1 0))
(safe '(+ 1 2))
