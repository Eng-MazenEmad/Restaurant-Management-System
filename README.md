# 🍽️ Restaurant Management System

A comprehensive Desktop-based **Restaurant Management & Ordering System** built using **Java** with an intuitive GUI. This system streamlines restaurant operations, covering everything from administrative tasks, meal inventory management, and customer relations to order placement and secure payment processing.

---

## 🚀 Key Features & Modules

### 1. Role-Based Access Control (Authentication)
* **Multi-Role Login:** Secure login supporting different user permissions (**Admin**, **Employee**, and **Customer**).
* **Role Dashboard:** Tailored dashboard interfaces based on the logged-in role.

### 2. Admin Dashboard
* **Manage Employees:** Full control over staff records, contact info, and roles.
* **Manage Meals:** Add, update, delete, search, and list menu items with pricing.
* **Manage Admin:** Administrative credential and privilege configurations.
* **Manage Reports:** Track overall system performance, sales, and statistics.
* **Offers & Rewards:** Handle promotional campaigns and customer loyalty programs.

### 3. Employee & Order Management System
* **Order System Interface:** 
  * View active menu items with IDs and prices.
  * Save items, specify quantities, assign order IDs and customer IDs.
  * Create, process, and delete orders dynamically.
  * Generate itemized bills.
* **Customer Manager:**
  * Register and manage customer profiles (Name, Phone, Address).
  * Track loyalty points and registration status.
  * Search customers by ID and refresh records instantly.
* **Payment System:**
  * Load orders using Order ID to calculate sub-totals.
  * Support for multiple payment methods.
  * Process payments and review individual or all historical bills.

---

## 🔑 Test Accounts (Default Login Credentials)

You can use the following default credentials to test different roles right away:

* **👑 Admin Login:**
  * **ID:** `11`
  * **Name:** `admin`
  * **Password:** `adminpass`

* **💼 Employee Login:**
  * **ID:** `111`
  * **Name:** `emp`
  * **Password:** `emppass`

---

## 📸 Application Screenshots

| Login Interface | Role Selection |
| :---: | :---: |
| ![Login Screen](images/Screenshot_2026-09-15_125332.png) | ![Role Selector](images/Screenshot_2026-09-15_125755.png) |

| Admin Dashboard | Employee Hub |
| :---: | :---: |
| ![Admin Dashboard](images/Screenshot_2026-09-15_125405.png) | ![Employee App](images/Screenshot_2026-09-15_125523.png) |

| Meal Management | Order System |
| :---: | :---: |
| ![Meal Management](images/Screenshot_2026-09-15_125420.png) | ![Order System](images/Screenshot_2026-09-15_125637.png) |

| Customer Manager | Payment System |
| :---: | :---: |
| ![Customer Manager](images/Screenshot_2026-09-15_125826.png) | ![Payment System](images/Screenshot_2026-09-15_125755.png) |

---

## 🛠️ Technology Stack
* **Language:** Java
* **IDE / Environment:** NetBeans / IntelliJ IDEA
* **Data Storage:** Flat-file / Text databases (`.txt` records for orders, customers, employees, and meals)

---

## ⚙️ Getting Started & Installation

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/Eng-MazenEmad/Restaurant-Management-System.git](https://github.com/Eng-MazenEmad/Restaurant-Management-System.git)
