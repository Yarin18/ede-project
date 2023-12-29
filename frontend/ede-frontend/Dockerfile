# Stage 1: Build Angular app
FROM node AS builder

WORKDIR /app

# Copy only the necessary files for the build
COPY frontend/ede-frontend/ /app

RUN npm install
RUN npm run build --prod

# Stage 2: Create Nginx container and copy built artifacts
FROM nginx:alpine

COPY --from=builder /app/dist/ede-frontend /usr/share/nginx/html