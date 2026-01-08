import GroupExpenses from "../features/GroupExpenses";
import GroupInfo from "../features/GroupInfo";
import GroupMembers from "../features/GroupMembers";

export default function GroupView() {
  return (
    <>
      <GroupInfo />
      <GroupMembers />
      <GroupExpenses />
    </>
  );
}
