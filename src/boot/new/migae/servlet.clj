(ns {{service}}.core
  "FIXME: This is the only servlet in this service, by default. To add others, specify them in config/servlets.edn"
  (:refer-clojure :exclude [read read-string])
  (:import #_[com.google.appengine.api.datastore EntityNotFoundException]
           [java.io InputStream ByteArrayInputStream]
           [java.util Collections]
           [java.lang IllegalArgumentException RuntimeException]
           [javax.servlet ServletConfig])
  (:require [clojure.tools.logging :as log :refer :all] ;; [debug info]] ;; :trace, :warn, :error, :fatal
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :as rsp]
            [ring.util.servlet :as ring]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.defaults :refer :all]))

(defroutes hello-routes
  (GET "/hello/:name" [name :as rqst]
       (do (log/info "{{service}} servlet, hello handler, rqst " (:request-method rqst)
                     (str (.getRequestURL (:servlet-request rqst))))
           (-> (rsp/response (str "Hi there " name ", from the HELLO handler of servlet: {{service}}.core"))
               (rsp/content-type "text/html"))))

  (route/not-found "<h1>route not found</h1>"))

;;;;;; javax.servlet.Servlet methods
;; (defn -init
;;   ([this]
;;    (.superInit this))
;;   ([this config]
;;    (.superInit this config))
;; (defn -getServletConfig
;;   [])
;; (defn -getServletInfo
;;   [])
;; (defn -destroy
;;   [this]
;;   (.superDestroy this))

;; required (defines -service method of javax.servlet.Servlet API):
(ring/defservice
   (-> (routes
        hello-routes)
       (wrap-defaults api-defaults)
       ))
