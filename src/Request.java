public class Request {
    int currentFloor;
    int destinationFloor;
    Direction direction;
    Location location;

    public Request(int currentFloor, int destinationFloor, Direction direction, Location location) {
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
        this.direction = direction;
        this.location = location;
    }


}
