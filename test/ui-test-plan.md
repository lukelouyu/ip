# UI Test Plan

This file is the source of truth for command-line UI regression tests.
Each test case runs in a fresh process. Output comparisons are exact except
that CRLF and LF line endings are treated as equivalent. Test execution
stops immediately after the first failure.

## Environment

- Java version: 25
- Main class: `luke.Luke`
- Source directory: `src/main/java`
- Runner: `.agents/skills/test-ui/scripts/run_ui_tests.py`
- Per-test timeout: 10 seconds

## Test cases

### UI-001: Exit immediately

**Aim:** Verify that Luke starts and exits cleanly without adding tasks.

**Inputs:**
```text
bye
```

**Expected output:**
```text
____________________________________________________________
Hello! I'm Luke
 _          _        
| |   _   _| | _____ 
| |  | | | | |/ / _ \
| |__| |_| |   <  __/
|_____\__,_|_|\_\___|

What can I do for you?
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

### UI-002: Add a Todo

**Aim:** Verify that a Todo is added and displayed with the correct type and status.

**Inputs:**
```text
todo borrow book
bye
```

**Expected output:**
```text
____________________________________________________________
Hello! I'm Luke
 _          _        
| |   _   _| | _____ 
| |  | | | | |/ / _ \
| |__| |_| |   <  __/
|_____\__,_|_|\_\___|

What can I do for you?
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [T][ ] borrow book
Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

### UI-003: Add a Deadline

**Aim:** Verify that a Deadline is added and displayed with its due date.

**Inputs:**
```text
deadline return book /by Sunday
bye
```

**Expected output:**
```text
____________________________________________________________
Hello! I'm Luke
 _          _        
| |   _   _| | _____ 
| |  | | | | |/ / _ \
| |__| |_| |   <  __/
|_____\__,_|_|\_\___|

What can I do for you?
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [D][ ] return book (by: Sunday)
Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

### UI-004: Add an Event

**Aim:** Verify that an Event is added and displayed with its start and end times.

**Inputs:**
```text
event project meeting /from Mon 2pm /to 4pm
bye
```

**Expected output:**
```text
____________________________________________________________
Hello! I'm Luke
 _          _        
| |   _   _| | _____ 
| |  | | | | |/ / _ \
| |__| |_| |   <  __/
|_____\__,_|_|\_\___|

What can I do for you?
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

### UI-005: List mixed task types

**Aim:** Verify that Todo, Deadline, and Event tasks retain their order and type-specific details when listed.

**Inputs:**
```text
todo borrow book
deadline return book /by Sunday
event project meeting /from Mon 2pm /to 4pm
list
bye
```

**Expected output:**
```text
____________________________________________________________
Hello! I'm Luke
 _          _        
| |   _   _| | _____ 
| |  | | | | |/ / _ \
| |__| |_| |   <  __/
|_____\__,_|_|\_\___|

What can I do for you?
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [T][ ] borrow book
Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [D][ ] return book (by: Sunday)
Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have 3 tasks in the list.
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1. [T][ ] borrow book
2. [D][ ] return book (by: Sunday)
3. [E][ ] project meeting (from: Mon 2pm to: 4pm)
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

### UI-006: Mark and unmark a typed task

**Aim:** Verify that a Deadline can be marked done, unmarked, and listed as incomplete again without losing its type details.

**Inputs:**
```text
deadline return book /by Sunday
mark 1
unmark 1
list
bye
```

**Expected output:**
```text
____________________________________________________________
Hello! I'm Luke
 _          _        
| |   _   _| | _____ 
| |  | | | | |/ / _ \
| |__| |_| |   <  __/
|_____\__,_|_|\_\___|

What can I do for you?
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [D][ ] return book (by: Sunday)
Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
Nice! I've marked this task as done:
  [D][X] return book (by: Sunday)
____________________________________________________________
____________________________________________________________
OK, I've marked this task as not done yet:
  [D][ ] return book (by: Sunday)
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1. [D][ ] return book (by: Sunday)
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```
