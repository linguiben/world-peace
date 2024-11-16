
提问: What is the default first day of the week in java language? 
A: Monday
B: Sunday
C: Friday
D: Saturday
E: Depends on the first day of the week setting in the operating system.
Answer:

Calendar: 
The combinations of the following are used to determine the Calendar.
https://github.com/openjdk/jdk8u/blob/master/jdk/src/share/classes/java/util/Calendar.java#L159

First day of the week in CLDR (weekData.json)
https://github.com/unicode-org/cldr-json/blob/main/cldr-json/cldr-core/supplemental/weekData.json#L120 //json
https://github.com/unicode-org/cldr/blob/main/common/supplemental/supplementalData.xml#L4889 //xml

first day of week 多种标准，例如：


1. CLDR标准(Common Locale Data Repository)：
   https://www.unicode.org/reports/tr35/tr35-dates.html#first-day-overrides
   https://www.unicode.org/reports/tr35/tr35-dates.html#Week_Data
3. java.util.Calendar: https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html
4. java 8开始支持ISO-8601标准，通过WeekFields.ISO.getFirstDayOfWeek()得到Monday. 根据ISO 8601标准，每周的第一天是星期一（Monday）。
   https://docs.oracle.com/javase/8/docs/api/java/time/temporal/WeekFields.html
```java
/*
A week is defined by:
The first day-of-week. For example, the ISO-8601 standard considers Monday to be the first day-of-week.
The minimal number of days in the first week. For example, the ISO-8601 standard counts the first week as needing at least 4 days.
Together these two values allow a year or month to be divided into weeks.
 */
```
5. 
java 8开始可以通过Locale.getDefault()获取当前语言环境，然后通过Locale.getISOCountries()获取当前语言环境的国家代码，

Calendar相关的bug:
https://bugs.java.com/bugdatabase/view_bug.do?bug_id=4655637 // Calendar.set() for DAY_OF_WEEK does not return the right value
https://bugs.java.com/bugdatabase/view_bug.do?bug_id=4685354 // Handling of Calendar fields setting state is broken
https://bugs.java.com/bugdatabase/view_bug?bug_id=4860664 // REGRESSION:Calendar get(Calendar.DAY_OF_WEEK)invalid under certain circumstances
https://bugs.java.com/bugdatabase/view_bug?bug_id=4727451 // GregorianCalendar constructors set invalid setting state for ERA
https://bugs.java.com/bugdatabase/view_bug.do?bug_id=4633646 // Setting WEEK_OF_MONTH to 1 results in incorrect date
https://bugs.java.com/bugdatabase/view_bug.do?bug_id=4153860 // SimpleDateFormat fails to parse redundant data


