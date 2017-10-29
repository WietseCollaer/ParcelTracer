# ParcelTracer
Android application for package tracing

## Quick Start Guide
**Get a list of all Trackings**

    TrackingController controller = new TrackingController();
    List<Tracking> trackings = controller.getTrackings();

**Get a tracking (by ID)**

    TrackingController controller = new TrackingController();
    Tracking tracking = controller.getTrackingById("59f64666fb17dff40b3d79ec");

**Get a tracking (by slug identifier and tracking number)**

    TrackingController controller = new TrackingController();
    Tracking tracking = controller.getTrackingByNumber("fedex","61297641751820041328");

**Post a tracking**

    TrackingController controller = new TrackingController();

    Map<String,String> customFields = new HashMap<>();
    customFields.put("category","Devices");

    Tracking tracking = new Tracking("Title","fedex","61297641751820041328",customFields);

    controller.addTracking(new AftershipResource(tracking));

**Delete a tracking (by ID)**

    TrackingController controller = new TrackingController();
    controller.deleteTrackingById("59f64666fb17dff40b3d79ec");

**Delete a tracking (by ID)**

    TrackingController controller = new TrackingController();
    controller.deleteTrackingByNumber("fedex","61297641751820041328");