(ns json-ip.main
  (:use ring.util.response
        ring.middleware.json
        ring.adapter.jetty))

(defn handler [request]
  (if-let [xri (get-in request [:headers "x-real-ip"])]
    (response {:ip xri})
    (if-let [xra (get-in request [:headers "x-remote-addr"])]
      (response {:ip xra})
      (if-let [xff (get-in request [:headers "x-forwarded-for"])]
        (response {:ip xff})
        (response {:ip (:remote-addr request)})))))

(def app
  (wrap-json-response handler))

(defn -main []
  (let [port (Integer/parseInt (get (System/getenv) "VCAP_APP_PORT" "8080"))]
    (run-jetty app {:port port})))

