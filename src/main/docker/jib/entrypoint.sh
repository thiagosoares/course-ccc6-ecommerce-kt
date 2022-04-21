#!/bin/sh

echo "The application will start in some seconds..." && sleep 30
exec java ${JAVA_OPTS} -noverify -XX:+AlwaysPreTouch -Djava.security.egd=file:/dev/./urandom -cp /app/resources/:/app/classes/:/app/libs/* "br.com.tfsoares.courses.branas.ecommerce.EcommerceApp"  "$@"
