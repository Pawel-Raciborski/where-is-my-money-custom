import { useState } from "react";
import CreateGroupForm from "../features/CreateGroupForm";

export default function CreateGroup() {
  const [showLink, setShowLink] = useState(false);

  function handleShowLink() {
    setShowLink(state => !state);
  }

  return (
    <div className="container">
      <div className="row justify-content-center align-items-center vh-100">
        <div className="col-md-4 col-sm-4">
          <div className="card rounded-3 p-4 shadow-sm">
            {showLink ? (
              <p>Dont show</p>
            ) : (
              <CreateGroupForm onShowLink={handleShowLink} />
            )}
          </div>
        </div>
      </div>
    </div>
  );
}
