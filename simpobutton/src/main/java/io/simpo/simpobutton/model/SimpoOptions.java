package io.simpo.simpobutton.model;

public class SimpoOptions {
    private SimpoUser user;
    private String uuid;
    private boolean show;
    private String position;
    private int width;
    private int height;

    public SimpoOptions() {
    }

    public SimpoOptions(SimpoUser user, String uuid, boolean show, String position, int width, int height) {
        this.user = user;
        this.uuid = uuid;
        this.show = show;
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public SimpoUser getUser() {
        return user;
    }


    public String getUuid() {
        return uuid;
    }

    public boolean isShow() {
        return show;
    }

    public String getPosition() {
        return position;
    }

    public String encodedOptions() {
        //let dimensions = String(format: "%.0fx%.0f", self.dimensions.width, self.dimensions.height)
        /*String data = "";
        data.
        var options = ["show": showWidget, "position": position.rawValue, "dimension": dimensions] as [String: Any]
        var user = [String: Any]()

        if let userEmail = userEmail { user["email"] = userEmail }
        if let userName = userName { user["name"] = userName }
        if !user.isEmpty { options["user"] = user }

        if let uuid = uuid { options["uuid"] = uuid }

        do {
            let jsonData = try JSONSerialization.data(withJSONObject: options)
            guard let encodedOptions = String(data: jsonData, encoding: String.Encoding.utf8),
            let encodedString = encodedOptions.addingPercentEncoding(withAllowedCharacters: CharacterSet.urlQueryAllowed) else {
                fatalError("Unable to encode options object")
            }
            return encodedString
        } catch {
            fatalError("Incorrect options object")
        }*/
        return "";
    }

}
