import "./App.css";
import GroupInfo from "./features/GroupInfo";
import GroupView from "./pages/GroupView";

function App() {
  return (
    <div className="container">
      <div className="row justify-content-center">
        <div className="col-md-10">
          <GroupView />
        </div>
      </div>
    </div>
  );
}

export default App;
