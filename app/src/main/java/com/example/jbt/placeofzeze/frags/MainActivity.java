package com.example.jbt.placeofzeze.frags;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.jbt.placeofzeze.model.AdapterRecycler;
import com.example.jbt.placeofzeze.model.FragAdapter;
import com.example.jbt.placeofzeze.R;
import com.example.jbt.placeofzeze.model.Place;

public class MainActivity extends AppCompatActivity implements AdapterRecycler.onClickItemFromList {

    private FragAdapter fragAdapter1;
    private ViewPager viewPager;
//    private RecyclerView recyclerViewFrag1;
    private int count = 0;
    private MapsActivity fragmentMapTab;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragAdapter1 = new FragAdapter(getSupportFragmentManager(),getResources().getBoolean(R.bool.isTablet));

        if (getResources().getBoolean(R.bool.isTablet)) {
            fragmentMapTab = (MapsActivity) getSupportFragmentManager().findFragmentById(R.id.fragmentMapTab);
        }

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(fragAdapter1);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0);

//        recyclerViewFrag1 = findViewById(R.id.recyclerViewSearch);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){//all menu cases

            case R.id.settings:
                startActivity(new Intent(this,SettingsActivity.class));//go to setting act
                break;

            case R.id.exit:
                finish();//exit LOL
                break;

            case R.id.AllPlaces://show all palces in list on map

                if (getResources().getBoolean(R.bool.isTablet)){
                    fragmentMapTab.allPlacesFromDB();

                }else {

                    MapsActivity mapFrag = (MapsActivity) fragAdapter1.getItem(1);
                    viewPager.setCurrentItem(1);
                    mapFrag.allPlacesFromDB();

                }

                break;


            case R.id.alon://  Alon "the airport" Mizrahi an Unknown genius of our time - check case 6
                //TODO toasts with int a++

                count++;
                       switch (count){

                           case 1:
                               Toast.makeText(this, "\"פרשתי מכדורגל אבל אני ממשיך לשחק.\" ~ בראיון ל-!Walla ספורט באוקטובר 2005 אחרי החתימה בעירוני עמישב מליגה ב'", Toast.LENGTH_LONG).show();
                               break;


                           case 2:
                               Toast.makeText(this, "\"אני לא יודע אם הוא ישמח, מה שבטוח הוא יקבל את זה בשמחה\" ~ על עודד מכנס לקראת עלייתו של אלון למקום הראשון בכובשי כל הזמנים במקום עודד", Toast.LENGTH_LONG).show();

                               break;

                           case 3:
                               Toast.makeText(this, " \"לדעתי מכבי ת\"א זו אחת הקבוצות שתיקח אליפות השנה בליגה\"", Toast.LENGTH_LONG).show();

                               break;

                           case 4:
                               Toast.makeText(this, "\"מה שהוא אמר אמר על גוטמן, זה הכי נכון\" \"מה הוא אמר?\" \"לא זוכר, אבל הוא אמר דברים נכונים\"", Toast.LENGTH_LONG).show();

                               break;

                           case 5:
                               Toast.makeText(this, "\"כדי להישאר בליגה נצטרך לנצח את כל הקבוצות, במיוחד אלה שנשחק נגדן.\"", Toast.LENGTH_LONG).show();

                               break;

                           case 6:
                               Toast.makeText(this, " \"עם הזמן השחקנים למדו לתת לי כבוד עצמי.\"", Toast.LENGTH_LONG).show();

                               break;

                           case 7:
                               Toast.makeText(this, "\"לרומא יש סיכוי מצוין לזכות בליגת האלופות, אם הם ימשיכו בכושר הנוכחי של השנה שעברה.\"", Toast.LENGTH_LONG).show();

                               break;

                           case 8:
                               Toast.makeText(this, "\"בגיל 30 הבנתי שכדורגל זה משחק קבוצתי.\"", Toast.LENGTH_LONG).show();

                               break;

                           case 9:
                               Toast.makeText(this, "\"אין לי מה להוכיח והוכחתי את זה היום על המגרש.\" ~ בראיון לערוץ 1 \"", Toast.LENGTH_LONG).show();

                               break;
                            //"זו לא ירידה ברמה לשחק בליגה ב′."
                           case 10:
                               Toast.makeText(this, R.string.alon, Toast.LENGTH_LONG).show();

                               break;

                           case 11:
                               Toast.makeText(this, " ההתקדמות שלי בכדורגל הישראלי היא כמו אוניברסיטה - אתה מקבל תעודה בכיתה י', תעודה בכיתה יא' ותעודה בכיתה יב'.\" ~ בטריבונה, יוני 2004", Toast.LENGTH_LONG).show();

                               break;

                           case 12:
                               Toast.makeText(this, "\"כדורסל הוא ספורט משעמם, לצפייה ולמשחק\"", Toast.LENGTH_LONG).show();

                               break;

                           case 13:
                               Toast.makeText(this, "\"אנחנו צריכים להפסיד כמה שפחות יותר נקודות עד סוף העונה.\" ~ כשנשאל לגבי מצבה של הפועל כפר־סבא.", Toast.LENGTH_LONG).show();

                               break;

                           case 14:
                               Toast.makeText(this, " \"אלון, היית מאוד פעיל במשחק.\"\"לא, דווקא הייתי אקטיבי.\"", Toast.LENGTH_LONG).show();

                               break;

                           case 15:
                               Toast.makeText(this, "\"שיחקנו הגנתי וניצחנו התקפי.\"", Toast.LENGTH_LONG).show();

                               break;

                           case 16:
                               Toast.makeText(this, "\"הייתי שלוש פעמים מלך השערים ואני מקום שישי בכובשי כל הזמנים, אז איך אני יכול להיות מטומטם?\"", Toast.LENGTH_LONG).show();

                               break;

                           case 17:
                               Toast.makeText(this, "\"אני משחק בקבוצת אם-בת של רומא.\" ~ לאחר שעזב את מכבי חיפה לטובת ניס מצרפת", Toast.LENGTH_LONG).show();

                               break;

                           case 18:
                               Toast.makeText(this, "\"אתה החלוץ מספר אחד בכדורגל הישראלי?\"\"אני לא אגיד לך את זה, אבל ברור שכן.\" ~ שם המשחק, 1997", Toast.LENGTH_LONG).show();

                               break;

                           case 19:
                               Toast.makeText(this, "\"תרשו לי לפרגן לעצמי, אני מאוד צנוע.\" ~ מעריב, 8 בדצמבר 2005", Toast.LENGTH_LONG).show();

                               break;

                           case 20:
                               Toast.makeText(this, "\"אני לא שוטף כלים בבית, אבל אם יש כוס או שתיים אני שוטף, זה הבית שלי.\"", Toast.LENGTH_LONG).show();

                               break;

                           case 21:
                               Toast.makeText(this, "\"צריך לתת את המקסימום המינימלי.\"", Toast.LENGTH_LONG).show();

                               break;

                           case 22:
                               Toast.makeText(this, "\"אל תהיה חכם, תהיה צודק.\" ~ כשמזרחי שימש כפרשן במשחק של רמת השרון מול נצרת עילית", Toast.LENGTH_LONG).show();

                               break;

                           case 23:
                               Toast.makeText(this, "\"אני בטוח שדמאיו עידכן אותו, או שלא עידכן אותו.\" ~ בטריבונה, יוני 2004", Toast.LENGTH_LONG).show();


                               break;

                           case 24:
                               Toast.makeText(this, "\"הדבר האחרון שיעזור לקולאוטי להשתקע בארץ הוא בריחה למדינה אחרת.\" ~ ספטמבר 2006", Toast.LENGTH_LONG).show();

                               break;

                           case 25:
                               Toast.makeText(this, "\"עדיף לחיות מכוער מאשר למות יפה.\" ~ בריאיון לערוץ הספורט", Toast.LENGTH_LONG).show();

                               break;

                           case 26:
                               Toast.makeText(this, " \"יש לי כל שני משחקים ממוצע של שער למשחק\"", Toast.LENGTH_LONG).show();

                               break;
                           case 27:
                               Toast.makeText(this, "\"אי-אפשר לומר שנוצר כאן שינוי של 180 אחוז\"", Toast.LENGTH_LONG).show();

                               break;


                           case 28:
                               Toast.makeText(this, "\"אני חושב שמי שתגמור עם הכי הרבה נקודות תזכה באליפות\"", Toast.LENGTH_LONG).show();

                               break;

                           case 29:
                               Toast.makeText(this, "\"הבעיה של הקבוצות הישראליות בגביעי אירופה,זה שכל משחק מגיעים לשלוש הזדמנויות ומבקיעים שני שערים, ואי אפשר להצליח ככה כשמנצלים 80 אחוז מהמצבים\"", Toast.LENGTH_LONG).show();

                               break;

                           case 30:
                               Toast.makeText(this, "\"היריב הבקיע גול אחד, אנחנו שלושה, סליחה, שלוש, ולמרות זאת ניצחנו\"", Toast.LENGTH_LONG).show();

                               break;

                           case 31:
                               Toast.makeText(this, "\"קיבלנו 2 אדומים, שיחקנו עם 8 שחקנים, ועדיין הפסדנו\"", Toast.LENGTH_LONG).show();

                               break;

                           case 32:
                               Toast.makeText(this, "\"יש שם 30 חברי הנהלה, 10 אומרים ככה ו-10 אומרים ככה\" ~ מסביר למה עזב את אח\"י נצרת.", Toast.LENGTH_LONG).show();

                               break;

                           case 33:
                               Toast.makeText(this, "\"אני הבקעתי גול אחד בכל שלושת המשחקים האחרונים,למרות זאת,הארבע גולים שהבקעתי בשלושת המשחקים האחרונים לא הספיקו\"", Toast.LENGTH_LONG).show();

                               break;

                           case 34:
                               Toast.makeText(this, "\"בית\"ר השתנתה ב- 360 מעלות\"", Toast.LENGTH_LONG).show();

                               break;

                           case 35:
                               Toast.makeText(this, "\"לחמישה מכל ארבעה אנשים יש בעיות במתמטיקה\"", Toast.LENGTH_LONG).show();

                               break;

                           case 36:
                               Toast.makeText(this, "\"אני רוצה לעבור (לגור) ליד הים כדי לראות בכל בוקר את הזריחה.\"", Toast.LENGTH_LONG).show();

                               break;

                           case 37:
                               Toast.makeText(this, "\"אני רוצה לשחק או באירופה או בספרד.\" ~ בראיון לערוץ הספורט.", Toast.LENGTH_LONG).show();

                               break;

                           case 38:
                               Toast.makeText(this, "\"מדהים אותי שכל הקבוצות בשמינית הגמר הן אירופאיות\" - מזרחי בפרשנות מעניינת ליורו 2004.", Toast.LENGTH_LONG).show();

                               break;

                           case 39:
                               Toast.makeText(this, "\"אני יודע שזה היה סופר\" ~ בתשובה לשאלת טריוויה: מי כתב את רומיאו ויוליה. 'העיר' 2001.", Toast.LENGTH_LONG).show();

                               break;

                           case 40:
                               Toast.makeText(this, "\"עכשיו תורי: אלון - עטלף.\" ~ אלון אומר את שמו ושם של חיה שלכאורה מתחילה באות הראשונה של השם שלו. מתוך התוכנית \"בסלון של האוירון\", 2006.", Toast.LENGTH_LONG).show();

                               break;

                           case 41:
                               Toast.makeText(this, " \"עשיתי בחסד ולא בזכות\"", Toast.LENGTH_LONG).show();
                               break;

                           case 42:
                               Toast.makeText(this, " \"יש חוסר אי-ודאות במכבי חיפה\"", Toast.LENGTH_LONG).show();

                               break;

                           case 43:
                               Toast.makeText(this, "\"אהרוני, אתה רשמת ספר מתכונים, חבל על הזמן. איזה כדורגלנים אתה מעריץ חוץ ממני?\" ~ לשף ישראל אהרוני, מתוך \"בסלון עם האווירון\", 2006.", Toast.LENGTH_LONG).show();

                               break;

                           case 44:
                               Toast.makeText(this, "\"הבעיה העונה הייתה ששוחררו 7-8 שחקנים מבלי שיהיה להם תחליף, וזה מה שספרתי רק על כף יד אחת\" ~ נאמר בפגישתו עם אלי זינו, ידיעות אחרונות. 31 במאי 2005.", Toast.LENGTH_LONG).show();

                               break;

                           case 45:
                               Toast.makeText(this, "\"אני לא תולה את הנעליים, אני פורש\"", Toast.LENGTH_LONG).show();

                               break;

                           case 46:
                               Toast.makeText(this, "\"כדורגלנים הם אנשים חכמים, יש לנו ראש על המתניים\"", Toast.LENGTH_LONG).show();

                               break;

                       }


                break;


        }
        return true;
    }

    @Override
    public void placeClicked(Place place) {
        // find the MapFrag and send this place - if tablet frag is there if phone go to frag

        if (getResources().getBoolean(R.bool.isTablet)) {
            fragmentMapTab.placeClickedFromList(place);

        }else {

            MapsActivity mapFrag = (MapsActivity) fragAdapter1.getItem(1);
            mapFrag.placeClickedFromList(place);
            viewPager.setCurrentItem(1);
        }
    }




}
