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
     (catch Exception e# e#))))


