FROM node AS builder
WORKDIR /app

COPY ./ /app

RUN npm install
RUN npm run build --prod

FROM nginx:alpine

COPY --from=builder /app/dist/ede-frontend /usr/share/nginx/html
