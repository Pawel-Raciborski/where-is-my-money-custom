import GroupExpenses from "../features/GroupExpenses";
import GroupInfo from "../features/GroupInfo";
import GroupMembers from "../features/GroupMembers";

export default function GroupPage() {
  return (
    <div className="container">
      <div className="row justify-content-center">
        <div className="col-md-10">
          <GroupInfo />
          <GroupMembers />
          <GroupExpenses />
        </div>
      </div>
    </div>
  );
}
