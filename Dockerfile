version: '3.8'

services:
  contabilidad-springboot:
    build:
      context: .
      dockerfile: Dockerfile
    container_name: contabilidad-springboot
    depends_on:
      - sqlserver
    ports:
      - "8080:8080"
    networks:
      - backend
    restart: on-failure

networks:
  backend:
    external: true
