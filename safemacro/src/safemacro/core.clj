(ns safemacro.core
  (:gen-class))

(import java.io.FileReader java.io.File)
(import java.io.Closeable)

(defmacro safe 
  ([expr]
   `(try 
     (eval ~expr)
     (catch Exception e# (str e#))))
  ([[^Closeable var value] expr]
   `(try
     (let [~var ~value]
      (eval ~expr))
     (catch Exception e# (str e#)) ;;ska returnera e
     (finally 
       (let [~var ~value]
         (if (instance? Closeable ~var)
          (. ~var close)))))))

(macroexpand '(safe [a 0] a))
(safe [a 0] (/ 1 a))
(safe [a 2] (+ a 22))
(safe (/ 2 0))
(macroexpand '(safe [a 2] (+ a 22)))

(safe [s (FileReader. (File. "hejhej.txt"))] (.read s))
(macroexpand '(safe [s (FileReader. (File. "hejhej.txt"))] (.read s)))

