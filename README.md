# ParcelTracer
Android application for package tracing

## Dependency Injection
**Preparing injection**

Add a new inject-method 'void inject(class)' in order to rely on dependency injection
    
    public interface NetComponent {
        void inject(TrackingController trackingController);
        void inject(HomeActivity activity);
    }
   
**Inject controller**

Inject the controller by using the "@Inject" annotation

    public class HomeActivity extends ActionBarActivity {
        @Inject
        private TrackingController controller;
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            ...
        }
    }
    
## Controllers
**Get a list of trackings**

    List<Tracking> trackings = controller.getTrackings();
    
**Get a tracking by ID**

    Tracking tracking = controller.getTrackingById("59f64666fb17dff40b3d79ec");
    
**Get a tracking by slug identifier and tracking number**

    Tracking tracking = controller.getTrackingByNumber("fedex","61297641751820041328");
    
**Post a tracking**

   Map<String,String> customFields;
   customFields.put("Category","Other");
   
   Tracking tracking = new Tracking("Title","fedex","61297641751820041328",customFields);
   
   controller.addTracking(new AftershipResource(tracking));
   
**Delete a tracking by ID**

   controller.deleteTrackingById("59f64666fb17dff40b3d79ec");
   
**Delete a tracking by slug identifier and tracking number**

   controller.deleteTrackingByNumber("fedex","61297641751820041328");
