(ns safemacro.core
  (:gen-class))

(defmacro safe 
  ([expr]
   `(try 
     (eval ~expr)
     (catch Exception e# (.getMessage e#))))
  ([vector expr]
   ;; gör kontroll av längd på vector
   `(try
     (let [^Closeable var ~(first vector)
            value ~(first (rest vector))
            var value]
        (eval ~expr)
        (. var close)
      (catch Exception e# (.getMessage e#))))))


(safe '([a 0] (/ 1 a)))

(safe [s (FileReader. (File. "file.txt"))] (.read s))

(safe '(/ 1 0))
(safe '(+ 1 2))
