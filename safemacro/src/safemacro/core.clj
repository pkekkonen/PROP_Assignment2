(ns safemacro.core
  (:gen-class))

(import java.io.FileReader java.io.File)
(import  java.io.Closeable)

(def f (FileReader. (File. "ex.txt")))


(def fr (new FileReader (File. "ex.txt")))

(instance? Closeable var)

(defmacro safe 
  ([expr]
   `(try 
     (eval ~expr)
     (catch Exception e# (.getMessage e#))))
  ([vector expr]
   ;; gör kontroll av längd på vector
   `(try
     (let [^Closeable var (~(first vector))] (def f ~(first (rest vector))
                                                  (let [var f]
                                                    (eval ~expr)
                                                    (if (instance? var Closeable)
                                                      (. var close))
                                                    (catch Exception e# (.getMessage e#))))))))


(safe [a 0] (/ 1 a))

(macroexpand '(safe [s (FileReader. (File. "file.txt"))] (.read s)))
(safe [s (FileReader. (File. "file.txt"))] (.read s))

(safe '(/ 1 0))
(safe '(+ 1 2))

<<<<<<< Updated upstream

(macroexpand '(safe [s (FileReader. (File. "missing-file"))] (. s read)))











=======
(defn hej [a] 
  (let [a (+ 1 1)
        b (+ 2 5)
        a b]
    a))

(hej ())

(defmacro testo 
  ([expr]
   `(try 
     (eval ~expr)
     (catch Exception e# (.getMessage e#))))
  ([vector expr]
   ;; gör kontroll av längd på vector
   `(try
     (let [var ~(first vector)
            value ~(first (rest vector))
            var value] (eval ~expr))
                            (catch Exception e# (.getMessage e#)))))
>>>>>>> Stashed changes




<<<<<<< Updated upstream
=======
(testo '[a 3] '(+ a 1))
(testo '(/ 9 0))
>>>>>>> Stashed changes







