import java.util.PriorityQueue;

public class Elevator {
    Direction direction;
    int currentFloor;
    PriorityQueue<Request> up;
    PriorityQueue<Request> down;

    public Elevator(Direction direction, int currentFloor) {
        this.direction = direction;
        this.currentFloor = currentFloor;
        this.up = new PriorityQueue<>((a,b)->(a.destinationFloor - b.destinationFloor));
        this.down = new PriorityQueue<>((a,b)->(b.destinationFloor - a.destinationFloor));
    }

    public void getUpRequest(Request upRequest){
        if(upRequest.location == Location.OUTSIDE_ELEVATOR){
            up.offer(new Request(upRequest.currentFloor, upRequest.currentFloor, Direction.UP, Location.OUTSIDE_ELEVATOR));
        }
        up.offer(upRequest);
    }

    public void getDownRequest(Request downRequest){
        if(downRequest.location == Location.OUTSIDE_ELEVATOR){
            down.offer(new Request(downRequest.currentFloor, downRequest.currentFloor, Direction.DOWN, Location.OUTSIDE_ELEVATOR));
        }
        down.offer(downRequest);
    }

    public void run(){
        while(!up.isEmpty() || !down.isEmpty()){
            processRequests();
        }
        System.out.println("Completed!");
    }

    public void processRequests(){
        if(this.direction == Direction.UP || this.direction == Direction.IDLE){
            processUpRequests();
            processDownRequests();
        }
        else{
            processDownRequests();
            processUpRequests();
        }
    }

    public void processUpRequests(){
        while(!up.isEmpty()){
            Request request = up.poll();
            this.currentFloor = request.destinationFloor;
        }
        if(!down.isEmpty()){
            this.direction = Direction.DOWN;
        }
        else{
            this.direction = Direction.IDLE;
        }
    }

    public void processDownRequests(){
        while(!down.isEmpty()){
            Request request = up.poll();
            this.currentFloor = request.destinationFloor;
        }
        if(!up.isEmpty()){
            this.direction = Direction.UP;
        }
        else{
            this.direction = Direction.IDLE;
        }
    }

    public static void main(String[] args) {

        Elevator e = new Elevator(Direction.IDLE, 0);

        Request r1 = new Request(0, 2, Direction.UP, Location.OUTSIDE_ELEVATOR);
        Request r2 = new Request(0, 3, Direction.UP, Location.OUTSIDE_ELEVATOR);
    }
}