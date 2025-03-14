package jsonparser;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
/**
 * A class for parsing JSON data received from a server providing object detection information.
 * The server exposes endpoints to retrieve the closest object or all detected objects.
 */
public class jsonParser {
    /**
     * The IP address of the server.
     */
    private String serverIpAddress;
    /**
     * The port number the server is listening on (usually 8008).
     */
    private int serverExposedPort;
    /**
     * Constructs a `jsonParser` object with the specified server IP address and port.
     *
     * @param ip   The IP address of the server.
     * @param port The port number the server is listening on.
     */
    public jsonParser(String ip, int port) {
        serverIpAddress = ip;
        serverExposedPort = port;
    }
    /**
     * Retrieves the object type of the closest detected object.
     *
     * @return The object type as a String.
     * @throws Exception if no object is found.
     */
    public String getClosestObjectClass() throws Exception {
        JsonObject jsonResponse = sendRequestToServer(serverIpAddress, Integer.toString(serverExposedPort), true);
        if (jsonResponse != null && jsonResponse.has("Objects")) {
            JsonElement objectsArray = jsonResponse.get("Objects");
            if (objectsArray != null && objectsArray.isJsonArray() && objectsArray.getAsJsonArray().size() > 0) {
                JsonArray jsonArray = objectsArray.getAsJsonArray();
                JsonObject obj = jsonArray.get(0).getAsJsonObject(); // Assuming the first element is the closest
                if (obj.has("object_type")) {
                    String objectType = obj.get("object_type").getAsString();
                    return objectType;
                } else {
                    throw new Exception("object_type not found in the JSON response.");
                }
            } else {
                throw new Exception("No objects found in the JSON array.");
            }
        } else {
            throw new Exception("No 'Objects' array found in the JSON response.");
        }
    }
    /**
     * Retrieves the distance in meters to the closest detected object.
     *
     * @return The distance to the closest object in meters.
     * @throws Exception if no object is found.
     */
    public int getClosestObjectDistance() throws Exception {
        JsonObject jsonResponse = sendRequestToServer(serverIpAddress, Integer.toString(serverExposedPort), true);
        if (jsonResponse != null && jsonResponse.has("Objects")) {
            JsonElement objectsArray = jsonResponse.get("Objects");
            if (objectsArray != null && objectsArray.isJsonArray() && objectsArray.getAsJsonArray().size() > 0) {
                JsonArray jsonArray = objectsArray.getAsJsonArray();
                JsonObject obj = jsonArray.get(0).getAsJsonObject(); // Assuming the first element is the closest
                if (obj.has("distance_meters")) {
                    int distanceMeters = obj.get("distance_meters").getAsInt();
                    return distanceMeters;
                } else {
                    throw new Exception("distance_meters not found in the JSON response.");
                }
            } else {
                throw new Exception("No objects found in the JSON array.");
            }
        } else {
            throw new Exception("No 'Objects' array found in the JSON response.");
        }
    }
    /**
     * Retrieves the horizontal angle relative to the camera of the closest detected object.
     *
     * @return The horizontal angle relative to the camera.
     * @throws Exception if no object is found.
     */
    public int getClosestObjecthorizontalAngle() throws Exception {
        JsonObject jsonResponse = sendRequestToServer(serverIpAddress, Integer.toString(serverExposedPort), true);
        if (jsonResponse != null && jsonResponse.has("Objects")) {
            JsonElement objectsArray = jsonResponse.get("Objects");
            if (objectsArray != null && objectsArray.isJsonArray() && objectsArray.getAsJsonArray().size() > 0) {
                JsonArray jsonArray = objectsArray.getAsJsonArray();
                JsonObject obj = jsonArray.get(0).getAsJsonObject(); // Assuming the first element is the closest
                if (obj.has("horizontal_angle_relative_to_camera")) {
                    int horizontalAngleRelativeToCamera = obj.get("horizontal_angle_relative_to_camera").getAsInt();
                    return horizontalAngleRelativeToCamera;
                } else {
                    throw new Exception("horizontal_angle_relative_to_camera not found in the JSON response.");
                }
            } else {
                throw new Exception("No objects found in the JSON array.");
            }
        } else {
            throw new Exception("No 'Objects' array found in the JSON response.");
        }
    }
    /**
     * Retrieves the vertical angle relative to the camera of the closest detected object.
     *
     * @return The vertical angle relative to the camera.
     * @throws Exception if no object is found.
     */
    public int getClosestObjectVerticalAngle() throws Exception {
        JsonObject jsonResponse = sendRequestToServer(serverIpAddress, Integer.toString(serverExposedPort), true);
        if (jsonResponse != null && jsonResponse.has("Objects")) {
            JsonElement objectsArray = jsonResponse.get("Objects");
            if (objectsArray != null && objectsArray.isJsonArray() && objectsArray.getAsJsonArray().size() > 0) {
                JsonArray jsonArray = objectsArray.getAsJsonArray();
                JsonObject obj = jsonArray.get(0).getAsJsonObject(); // Assuming the first element is the closest
                if (obj.has("vertical_angle_relative_to_camera")) {
                    int verticalAngleRelativeToCamera = obj.get("vertical_angle_relative_to_camera").getAsInt();
                    return verticalAngleRelativeToCamera;
                } else {
                    throw new Exception("vertical_angle_relative_to_camera not found in the JSON response.");
                }
            } else {
                throw new Exception("No objects found in the JSON array.");
            }
        } else {
            throw new Exception("No 'Objects' array found in the JSON response.");
        }
    }
    /**
     * Retrieves the object type of the object at the specified index in the list of detected objects.
     *
     * @param index The index of the object in the list.
     * @return The object type as a String.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     * @throws Exception if no objects are found.
     */
    public String getObjectClassAtIndex(int index) throws Exception {
        JsonObject jsonResponse = sendRequestToServer(serverIpAddress, Integer.toString(serverExposedPort), false);
        if (jsonResponse != null && jsonResponse.has("Objects")) {
            JsonElement objectsArray = jsonResponse.get("Objects");
            if (objectsArray != null && objectsArray.isJsonArray()) {
                JsonArray jsonArray = objectsArray.getAsJsonArray();
                if (index >= 0 && index < jsonArray.size()) {
                    JsonObject obj = jsonArray.get(index).getAsJsonObject();
                    if (obj.has("object_type")) {
                        String objectType = obj.get("object_type").getAsString();
                        return objectType;
                    } else {
                        throw new Exception("object_type not found in the JSON response.");
                    }
                } else {
                    throw new IndexOutOfBoundsException("Index out of bounds: " + index);
                }
            } else {
                throw new Exception("No objects found. The 'Objects' element is not a JSON array.");
            }
        } else {
            throw new Exception("No 'Objects' array found in the JSON response.");
        }
    }
    /**
     * Retrieves the distance in meters to the object at the specified index in the list of detected objects.
     *
     * @param index The index of the object in the list.
     * @return The distance to the object in meters.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     * @throws Exception if no objects are found.
     */
    public int getObjectDistanceAtIndex(int index) throws Exception {
        JsonObject jsonResponse = sendRequestToServer(serverIpAddress, Integer.toString(serverExposedPort), false);
        if (jsonResponse != null && jsonResponse.has("Objects")) {
            JsonElement objectsArray = jsonResponse.get("Objects");
            if (objectsArray != null && objectsArray.isJsonArray()) {
                JsonArray jsonArray = objectsArray.getAsJsonArray();
                if (index >= 0 && index < jsonArray.size()) {
                    JsonObject obj = jsonArray.get(index).getAsJsonObject();
                    if (obj.has("distance_meters")) {
                        int distanceMeters = obj.get("distance_meters").getAsInt();
                        return distanceMeters;
                    } else {
                        throw new Exception("distance_meters not found in the JSON response.");
                    }
                } else {
                    throw new IndexOutOfBoundsException("Index out of bounds: " + index);
                }
            } else {
                throw new Exception("No objects found. The 'Objects' element is not a JSON array.");
            }
        } else {
            throw new Exception("No 'Objects' array found in the JSON response.");
        }
    }
    /**
     * Retrieves the horizontal angle relative to the camera of the object at the specified index in the list of detected objects.
     *
     * @param index The index of the object in the list.
     * @return The horizontal angle relative to the camera.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     * @throws Exception if no objects are found.
     */
    public int getObjecthorizontalAngleAtIndex(int index) throws Exception {
        JsonObject jsonResponse = sendRequestToServer(serverIpAddress, Integer.toString(serverExposedPort), false);
        if (jsonResponse != null && jsonResponse.has("Objects")) {
            JsonElement objectsArray = jsonResponse.get("Objects");
            if (objectsArray != null && objectsArray.isJsonArray()) {
                JsonArray jsonArray = objectsArray.getAsJsonArray();
                if (index >= 0 && index < jsonArray.size()) {
                    JsonObject obj = jsonArray.get(index).getAsJsonObject();
                    if (obj.has("horizontal_angle_relative_to_camera")) {
                        int horizontalAngleRelativeToCamera = obj.get("horizontal_angle_relative_to_camera").getAsInt();
                        return horizontalAngleRelativeToCamera;
                    } else {
                        throw new Exception("horizontal_angle_relative_to_camera not found in the JSON response.");
                    }
                } else {
                    throw new IndexOutOfBoundsException("Index out of bounds: " + index);
                }
            } else {
                throw new Exception("No objects found. The 'Objects' element is not a JSON array.");
            }
        } else {
            throw new Exception("No 'Objects' array found in the JSON response.");
        }
    }
    /**
     * Retrieves the vertical angle relative to the camera of the object at the specified index in the list of detected objects.
     *
     * @param index The index of the object in the list.
     * @return The vertical angle relative to the camera.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     * @throws Exception if no objects are found.
     */
    public int getObjectVerticalAngleAtIndex(int index) throws Exception {
        JsonObject jsonResponse = sendRequestToServer(serverIpAddress, Integer.toString(serverExposedPort), false);
        if (jsonResponse != null && jsonResponse.has("Objects")) {
            JsonElement objectsArray = jsonResponse.get("Objects");
            if (objectsArray != null && objectsArray.isJsonArray()) {
                JsonArray jsonArray = objectsArray.getAsJsonArray();
                if (index >= 0 && index < jsonArray.size()) {
                    JsonObject obj = jsonArray.get(index).getAsJsonObject();
                    if (obj.has("vertical_angle_relative_to_camera")) {
                        int verticalAngleRelativeToCamera = obj.get("vertical_angle_relative_to_camera").getAsInt();
                        return verticalAngleRelativeToCamera;
                    } else {
                        throw new Exception("vertical_angle_relative_to_camera not found in the JSON response.");
                    }
                } else {
                    throw new IndexOutOfBoundsException("Index out of bounds: " + index);
                }
            } else {
                throw new Exception("No objects found. The 'Objects' element is not a JSON array.");
            }
        } else {
            throw new Exception("No 'Objects' array found in the JSON response.");
        }
    }
    /**
     * Sends a request to the server to retrieve object detection data.
     *
     * @param ip           The IP address of the server.
     * @param port         The port number of the server.
     * @param closestOrAll A boolean indicating whether to retrieve only the closest object (true) or all objects (false).
     * @return A `JsonObject` representing the JSON response from the server, or `null` if an error occurred.
     */
    private JsonObject sendRequestToServer(String ip, String port, boolean closestOrAll) {
        String endpoint = closestOrAll ? "/get_closest_object" : "/get_all_objects";
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://" + ip + ":" + port + endpoint))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
            System.out.println("MIME Type: " + response.headers().firstValue("Content-Type").orElse("N/A"));
            if (response.statusCode() == 200
                    && "application/json".equals(response.headers().firstValue("Content-Type").orElse("N/A"))) {
                JsonObject jsonObject = new JsonParser().parse(response.body()).getAsJsonObject();
                return jsonObject;
            } else {
                System.err.println("Error: Invalid response from server. Status code: " + response.statusCode() + ", Content-Type: " + response.headers().firstValue("Content-Type").orElse("N/A"));
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}