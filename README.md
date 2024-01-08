# Phone Book

A phone book console file-based Java application.

---

## Sample Data

### File 1

```
name James Smith
birthday 23-3-1989
address 60 Morris St, Summer Hill, NSW 2130

name Posephine Esmerelda Bloggs
birthday 13-05-1980
phone 16549853
email j.e.bloggs@gmail.com
address 102 Smith St, Summer hill, NSW 2130

address 2 Fairmount St, Dulwich Hill, NSW 2203
name John Jones
birthday 18-09-1968
phone 13649852

email robert.taylor@gmail.com
address 36 Carrington St, Summer Hill, NSW 2130
name Robert Taylor
birthday 3-6-2008

```

### File 2

```
name Posephine Esmerelda Bloggs
birthday 13-05-1980
phone 16549853
email j.e.bloggs@gmail.com
address 102 Smith St, Summer hill, NSW 2130

name James Smith
birthday 23-3-1989
phone 98765425
email j.smith@gmail.com
address 60 Morris St, Summer Hill, NSW 2130

name John Jones
birthday 18-09-1968
phone 13649852
email j.jones@fanfare.com.au
address 2 Fairmount St, Dulwich Hill, NSW 2203

```

---

## Sample Instructions

### File 1

```
add name Jo Bloggs; birthday 08-07-1900; phone 88884444; address 9001 Chester Crescent, Chatswood, NSW 2057 

add email michael@fanfare.com.au; name Michael Willianm; birthday 01-4-1950;   address 37 Barton Ave, Haberfield, NSW 2045; phone 19876245

add name Linda Evans; birthday 22-02-1996;  phone 135625; email l.evans@gmail.com; address 119 Gibbes St, Rockdale, NSW 2216

add address 64 Lennox St, Rockdale, NSW 2216; name Barbara Wilson; birthday 3-01-1988; phone 0469832165; email b.wilson@fanfare.com.au

delete James Smith; 23-3-1989
delete John Jones; 18-09-1968
delete Linda Evans; 22-02-1996

add name Posephine Esmerelda Bloggs; birthday 13-05-1980; phone 98765432
add name Jo Bloggs; birthday 08-07-1900

save

```

### File 2

```
query name Posephine Esmerelda Bloggs

query birthday 05-09-1980

query phone 13589634

save

```