(ns json-ip.main
  (:use ring.util.response
        ring.middleware.json
        ring.adapter.jetty))

(defn handler [request]
  (if-let [ip (or (get-in request [:headers "x-cluster-client-ip"]) (get-in request [:headers "x-real-ip"]) (get-in request [:headers "x-remote-addr"]) (get-in request [:headers "x-forwarded-for"]))]
    (response {:ip ip})
    (response {:headers (get request :headers) :ip (:remote-addr request)})
  ))

(def app
  (wrap-json-response handler))

(defn -main []
  (let [port (Integer/parseInt (get (System/getenv) "VCAP_APP_PORT" "8080"))]
    (run-jetty app {:port port})))

