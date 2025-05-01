# 🧾 Accounting Ledger - Java Console App

This is a simple app made with **Java** that lets you keep track of money you spend or receive. It saves everything into a file called `transactions.csv`. You use it from the command line.

---

## 🛠️ Tools Used
- Java
- Scanner (for user input)
- BufferedReader / BufferedWriter (to read/write CSV)
- Object-Oriented Programming

---

## ✅ What You Can Do
- Add a **deposit** (money you got)
- Add a **payment** (money you spent)
- See your ledger:
    - All transactions
    - Only deposits
    - Only payments
- Run different reports:
    - Month to date
    - Previous month
    - Year to date
    - Previous year
    - Search by vendor
    - 🔎 Custom search by:
        - Start date
        - End date
        - Description
        - Vendor
        - Amount

---

## ▶️ How to Run It
1. Download or clone the repo:
```bash
git clone https://github.com/your-username/ledger-app.git
```
2. Go to the project folder and compile:
```bash
javac com/ps/*.java
```
3. Run the app:
```bash
java com.ps.Main
```

📁 A file named `transactions.csv` will be created if it doesn’t exist.

---

## 💻 What It Looks Like
### Deposit Example:
```
Enter description: Freelance Work
Enter vendor: Upwork
Enter amount: 850.0
✅ Deposit added successfully!
```

### Custom Search Example:
```
Enter start date: 2025-01-01
Enter end date: 2025-01-31
Enter vendor: amazon
✅ 2 transactions matched
```

---

## 💡 Code Highlight
```java
if (startDate != null && date.isBefore(startDate)) matches = false;
if (endDate != null && date.isAfter(endDate)) matches = false;
if (!vendorInput.isEmpty() && !vendor.contains(vendorInput)) matches = false;
```
☝ This lets the user search with only the fields they want. If they leave something blank, the program ignores it.

---

## 👨‍💻 Want to Help?
- Fork this repo
- Make your changes on a new branch
- Open a pull request (PR)

---

## 📃 License
MIT License (You can use and change this freely)

---

## 🔗 Useful Links
- [Java File I/O](https://docs.oracle.com/javase/tutorial/essential/io/)
- [Markdown Basics](https://www.markdownguide.org/cheat-sheet/)

