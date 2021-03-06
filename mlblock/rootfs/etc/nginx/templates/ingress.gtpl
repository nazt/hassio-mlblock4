server {
    listen {{ .interface }}:{{ .port }} default_server;

    include /etc/nginx/includes/server_params.conf;
    include /etc/nginx/includes/proxy_params.conf;

    location / {
        allow   172.30.32.2;
        deny    all;

        proxy_pass http://backend;

        sub_filter_once off;
        sub_filter_types *;

        sub_filter '/aaamlblock' '{{ .interface }}';
        sub_filter '/xxxmlblock' '{{ .ingress_entry }}';
    }
}
