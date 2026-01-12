import CreateGroupForm from "../features/CreateGroupForm";

export default function CreateGroup() {
  return (
    <div className="container">
      <div className="row justify-content-center align-items-center vh-100">
        <div className="col-md-4 col-sm-4">
          <CreateGroupForm />
        </div>
      </div>
    </div>
  );
}
