# Shopping Cart Application

## 📋 Overview
The **Shopping Cart Application** is an intuitive e-commerce backend solution designed to manage product listings, shopping carts, and customer orders. It streamlines the process of browsing, adding products to the cart, and finalizing purchases.

## 🚀 Key Features
- 🛍️ Product Catalog Management
- ➕ Add/Remove/Update Cart Items
- ✅ Order Processing & Checkout
- 🔄 Increment/Decrement Product Quantity
- 🗑️ Remove Individual or All Products from Cart
- ⚠️ Robust Exception Handling for Carts and Products

## 🛠️ Tech Stack
- **Java**: Core backend development
- **Spring Boot**: REST API framework
- **Maven**: Build and dependency management
- **MySQL**: Database for product & order data

## 📦 API Endpoints
### Cart Operations:
- **Add Product to Cart:** `PUT /user/cart/{userId}/addProduct/{productId}`
- **Get Cart by User ID:** `GET /user/cart/{userId}`
- **Remove Product from Cart:** `PUT /user/cart/{userId}/removeProduct/{productId}`
- **Clear Cart:** `PUT /user/cart/{userId}/removeProduct/all`
- **Increment Product Quantity:** `PUT /user/cart/{userId}/productQuantity/increment/{productId}`
- **Decrement Product Quantity:** `PUT /user/cart/{userId}/productQuantity/decrement/{productId}`

### Product Operations:
- **Get Product by ID:** `GET /product/id/{id}`
- **Get Product by Name:** `GET /product/name/{name}`
- **Get Products by Category:** `GET /product/category/{category}`
