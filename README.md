# ReelCounter

ReelCounter is a Java Swing desktop application that tracks daily reel swipes and stores the data locally using SQLite. The application automatically saves swipe counts, restores today's progress after restarting, maintains a complete history, and fills skipped dates with zero entries to keep daily records consistent.

---

##  Features

- ✅ Count reel swipes with a single click
- ✅ Mouse wheel scrolling also increments the swipe count
- ✅ Automatic saving using SQLite
- ✅ Restores today's swipe count after application restart
- ✅ Daily swipe history viewer
- ✅ Automatically inserts zero-count entries for skipped days
- ✅ Simple and user-friendly Swing interface

---

##  Technologies Used

- Java
- Java Swing
- JDBC
- SQLite
- NetBeans IDE
- Ant Build System

---

##  Project Structure

```
ReelCounter
│
├── Main.java
├── SwipePanel.java
├── Database.java
├── HistoryFrame.java
└── reel.db
```

---

## ⚙️ How It Works

1. The application starts by loading today's swipe count from the SQLite database.
2. Every button click or mouse wheel scroll increases the swipe count.
3. The updated count is immediately saved to the database.
4. If the application is closed and reopened on the same day, the previous count is restored automatically.
5. If one or more calendar days are skipped, the application automatically creates zero-count records for those missing dates.
6. The History window displays all recorded dates and their swipe counts.

---

## 💾 Database

The application uses a local SQLite database named:

```
reel.db
```

Table:

```
reel_history
```

Columns:

- date
- count

---

## 🚀 Getting Started

1. Clone the repository.

2. Open the project in NetBeans.

3. Add the SQLite JDBC driver.

4. Run the project.

## 📸 Screenshots

![image alt](https://github.com/vk9056871-code/Reel-Counter/blob/2edf3193f177c6529f6f57cca8788044cf8d2eee/Screenshot%202026-07-17%20151706.png)
![image alt](https://github.com/vk9056871-code/Reel-Counter/blob/bd119ed2b0b51d1e304fa2fa291f9b5bb0a00855/Screenshot%202026-07-17%20151610.png)
![image alt](https://github.com/vk9056871-code/Reel-Counter/blob/03d175823465f34b93fea4505a27cea2750906aa/Screenshot%202026-07-17%20151640.png)

## Author

Developed by **Vikash Kumar**
