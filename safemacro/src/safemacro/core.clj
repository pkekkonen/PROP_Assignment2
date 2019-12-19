(ns safemacro.core
  (:gen-class))

(import java.io.FileReader java.io.File)
(import java.io.Closeable)

(def ^:dynamic var2 0)
(def ^:dynamic value2 0)
(def ^:dynamic res 0)

(defn safe 
  ([expr]
<<<<<<< Updated upstream
   `(try 
     (eval ~expr)
     (catch Exception e# (.getMessage e#))))
  ([[^Closeable var value] expr]
   `(try
     (let [~var ~value]
      (eval ~expr))
     (catch Exception e# (.getMessage e#))
     (finally 
      (let [~var ~value]
       (if (instance? Closeable ~var)
        (. ~var close)))))))

(macroexpand '(safe [a 0] a))
(safe [a 0] (/ 1 a))
(safe [a 2] (+ a 22))
(macroexpand '(safe [a 2] (+ a 22)))

(def v (safe [s (FileReader. (File. "hejhej.txt"))] (.read s)))
(macroexpand '(safe [s (FileReader. (File. "hejhej.txt"))] (.read s)))

(do v)
=======
   (try 
     (eval expr)
     (catch Exception e (str e))))
  ([[^Closeable var value] expr]
   (try    
     (binding [var2 var 
               value2 value 
               var2 value2 
               res (eval expr)]
       
       (if (instance? Closeable var)
         (. var close))
       res)
     (catch Exception e (str e))))) ;;ska returnera e

  
(safe (+ 1 10))
  
(safe [a 2] (+ a 22)
  (println p))
  
(safe (/ 2 0)
  (println g))
  
(safe [s (FileReader. (File. "/Users/paulinakekkonen/Desktop/hejhej.txt"))] (.read s)
  (println v))
  
(safe [s (FileReader. (File. "dhejhej.txt"))] (.read s)
  (println w)) 




>>>>>>> Stashed changes
