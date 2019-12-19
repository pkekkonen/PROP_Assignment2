(ns safemacro.core
  (:gen-class))

(import java.io.FileReader java.io.File)
(import java.io.Closeable)

(def ^:dynamic res 0)

(def ^:dynamic var 0)


(defn safe 
  ([expr]
   (try 
     (eval expr)
     (catch Exception e (str e))))
  ([[^Closeable var value] expr]
   (try    
     (binding [
               var value
               res (eval expr)]
       
       (if (instance? Closeable var)
         (. var close))
       res)
     (catch Exception e (str e))))) ;;ska returnera e

  
(safe (+ 1 10))

(safe [a 2] (+ a 22))

  
(safe '(/ 2 0))
  
(safe [s (FileReader. (File. "/Users/paulinakekkonen/Desktop/hejhej.txt"))] '(.read s))

  
(safe [s (FileReader. (File. "dhejhej.txt"))] '(.read s)) 


