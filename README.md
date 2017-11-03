# ParcelTracer
Android application for package tracing

## Dependency Injection
**Preparing injection**

Add a new inject-method 'void inject(class)' in order to rely on dependency injection
    
    public interface NetComponent {
        void inject(TrackingRepository repository);
        void inject(HomeActivity activity);
    }
   
**Inject controller**

Inject the controller by using the "@Inject" annotation and add '((App)getApplication()).getNetComponent().inject(this)'

    public class HomeActivity extends ActionBarActivity {
        @Inject
        private TrackingRepository repository;
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            ...
            ((App)getApplication()).getNetComponent().inject(this);
        }
    }
    
## Controllers
**Get a list of trackings**

    List<Tracking> trackings = repository.getTrackings();
    
**Get a tracking by ID**

    Tracking tracking = repository.getTrackingById("59f64666fb17dff40b3d79ec");
    
**Get a tracking by slug identifier and tracking number**

    Tracking tracking = repository.getTrackingByNumber("fedex","61297641751820041328");
    
**Post a tracking**

    Map<String,String> customFields;
    customFields.put("Category","Other");
   
    Tracking tracking = new Tracking("Title","fedex","61297641751820041328",customFields);
   
    repository.addTracking(new AftershipResource(tracking));
   
**Delete a tracking by ID**

    repository.deleteTrackingById("59f64666fb17dff40b3d79ec");
   
**Delete a tracking by slug identifier and tracking number**

    repository.deleteTrackingByNumber("fedex","61297641751820041328");
