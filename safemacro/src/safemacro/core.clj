(ns safemacro.core
  (:gen-class))

(import java.io.FileReader java.io.File)
(import java.io.Closeable)

(defmacro safe 
  ([expr]
   `(try 
     (eval ~expr)
     (catch Exception e# e#)))
  ([[^Closeable var value] expr]
   `(try    
     (let [~var ~value res# (eval ~expr)]
  
      (if (instance? Closeable ~var)
       (. ~var close))
      res#)
     (catch Exception e# e#)))) ;;ska returnera e

(defn -main [& args]
  
  
  (def f(safe (+ 1 10)))
  (println f)
  
  (def p(safe [a 2] (+ a 22)))
  (println p)
  
  (def g(safe (/ 2 0)))
  (println g)
  
  (def v (safe [s (FileReader. (File. "/Users/paulinakekkonen/Desktop/hejhej.txt"))] (.read s)))
  (println v)
  
  (def w(safe [s (FileReader. (File. "dhejhej.txt"))] (.read s)))
  (println w)) 





