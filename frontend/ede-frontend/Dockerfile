FROM node AS builder
WORKDIR /app

COPY . .
RUN npm install
RUN npm run build --prod

# Stage 2: Create Nginx container and copy built artifacts
FROM nginx:alpine
COPY --from=builder /app/dist/edeproject /usr/share/nginx/html