(ns safemacro.core
  (:gen-class))

(defmacro safe 
  [vector expr]
  (is (thrown? Exception (expr))))
  
  
  
  
  
  
  
