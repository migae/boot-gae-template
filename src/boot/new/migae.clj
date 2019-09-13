(ns boot.new.migae
  (:require [boot.new.templates :as bnt]))

(def render (bnt/renderer "migae"))

(defn migae
  "FIXME: write documentation"
  ;; args: project name, service-name, namespace for sample servlet,
  ;; e.g. service-x, foo.bar.hello
  [name & services]
  (println name " has the following args: " services)
  (if (seq services)
    (let [svcs (seq (zipmap (iterate inc 8080) services))
          _ (println "svcs: " svcs)
          ss (into [] (map (fn [[k v]]
                             {:port k :service v :wardir (bnt/name-to-path v)}) svcs))
          data {:name name
                :project name
                :services ss
                }]
      (println "Generating migae application " name " with data: " data)
      (bnt/->files data
                   ["build.boot" (render "app.boot" data)])
      (doseq [service services]
        (let [data {:name name
                    :service service
                    :sanitized (bnt/name-to-path service)}]
          (binding  [bnt/*force?* true]
            (bnt/->files data
                         ;;["build.boot" (render "app.boot" data)]
                         ["{{sanitized}}/README.md" (render "app.README.md" data)]
                         ["{{sanitized}}/build.boot" (render "service.boot" data)]
                         ["{{sanitized}}/config/appengine.edn" (render "config/appengine.edn" data)]
                         ["{{sanitized}}/config/filters.edn" (render "config/filters.edn" data)]
                         ["{{sanitized}}/config/jul.edn" (render "config/jul.edn" data)]
                         ["{{sanitized}}/config/log4j.edn" (render "config/log4j.edn" data)]
                         ["{{sanitized}}/config/security.edn" (render "config/security.edn" data)]
                         ["{{sanitized}}/config/servlets.edn" (render "config/servlets.edn" data)]
                         ["{{sanitized}}/config/webapp.edn" (render "config/webapp.edn" data)]
                         ["{{sanitized}}/resources/public/favicon.ico" (render "resources/public/favicon.ico" data)]
                         ["{{sanitized}}/resources/public/index.html" (render "resources/public/index.html" data)]
                         ["{{sanitized}}/src/clj/{{sanitized}}/core.clj" (render "servlet.clj" data)]
                         ["{{sanitized}}/src/clj/{{sanitized}}/core/filter.clj" (render "filter.clj" data)])))))
    ;; not services
    (let [data {:service name
                :name (bnt/name-to-path name)
                :servlet-path (bnt/name-to-path name)}]
      (println "Generating new migae service: " name)
      (bnt/->files data
                   ["README.md" (render "service.README.md" data)]
                   ["build.boot" (render "service.boot" data)]
                   ["config/appengine.edn" (render "config/appengine.edn" data)]
                   ["config/filters.edn" (render "config/filters.edn" data)]
                   ["config/jul.edn" (render "config/jul.edn" data)]
                   ["config/log4j.edn" (render "config/log4j.edn" data)]
                   ["config/security.edn" (render "config/security.edn" data)]
                   ["config/servlets.edn" (render "config/servlets.edn" data)]
                   ["config/webapp.edn" (render "config/webapp.edn" data)]
                   ["resources/public/favicon.ico" (render "resources/public/favicon.ico" data)]
                   ["resources/public/index.html" (render "resources/public/index.html" data)]
                   ["src/clj/{{servlet-path}}/core.clj" (render "servlet.clj" data)]
                   ["src/clj/{{servlet-path}}/core/filter.clj" (render "filter.clj" data)]))
    ))
