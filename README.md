# PersianDatePicker
This Library is a date picker for Android to pick a date and only supports a year from the day you open the app.


### Usage
First add jitpack to your projects build.gradle file

```gradle
allprojects {
   	repositories {
   		...
   		maven { url "https://jitpack.io" }
   	}
}
```

Then add the dependency in your android app module's `build.gradle` file.

```gradle
dependencies {
    implementation 'com.github.Behkha:Persian-Horizontal-Date-Picker:0.1.1'
}
```

Then use the view in your layouts:

```xml
<com.ratintech.behkha.persiandatepicker.PersianDatePicker
        android:id="@+id/persian_date_picker"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        >
```


### Setup

``` java
PersianDatePicker persianDatePicker = findViewById(R.id.persian_date_picker);
        persianDatePicker.setYearMonths( new Calendar( new PersianCalendar().getPersianLongDate()).getYearMonths() )
                .setDefaultItemBackgroundColor(R.color.defaultBackgroundColor)  // background color of non-selected item
                .setDefaultItemTextColor(R.color.defaultTextColor)  // text color of non-selected item
                .setSelectedItemBackgroundColor(R.color.colorPrimary) // background color of selected item
                .setSelectedItemTextColor(R.color.selectText)   // text color of selected item
                .setListener(new PersianDatePicker.OnDaySelectListener() {
                    @Override
                    public void onDaySelect(YearMonth yearMonth, Day day) {
                        // right your code here when item is selected 
                    }
                })
                .setItemElevation( 4f )  // default is 0
                .setItemRadius( 2f )  // default is 0
                .hasAnimation(true) // Animation for item selection . default is false 
                .load();
```
