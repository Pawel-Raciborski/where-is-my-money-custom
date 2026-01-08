import Member from "./Member";

export default function GroupMembers() {
  const members = [
    {
      id: 1,
      name: "Andrzej",
    },
    {
      id: 2,
      name: "Tomek",
    },
  ];

  return (
    <div className="row p-3 border border-2 rounded-2 mt-5 shadow-sm">
      <div className="col-md-12 mt-2 mb-2">
        <div className="row">
          <div className="col-md-10">
            <p className="fs-4 d-flex align-items-center gap-2">
              <span>
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke-width="1.6"
                  stroke="currentColor"
                  className="icon-very-small"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    d="M15 19.128a9.38 9.38 0 0 0 2.625.372 9.337 9.337 0 0 0 4.121-.952 4.125 4.125 0 0 0-7.533-2.493M15 19.128v-.003c0-1.113-.285-2.16-.786-3.07M15 19.128v.106A12.318 12.318 0 0 1 8.624 21c-2.331 0-4.512-.645-6.374-1.766l-.001-.109a6.375 6.375 0 0 1 11.964-3.07M12 6.375a3.375 3.375 0 1 1-6.75 0 3.375 3.375 0 0 1 6.75 0Zm8.25 2.25a2.625 2.625 0 1 1-5.25 0 2.625 2.625 0 0 1 5.25 0Z"
                  />
                </svg>
              </span>
              <span>Uczestnicy</span>
            </p>
          </div>
          <div className="col-md-2">
            <button className="btn add-member-btn btn-outline-light text-dark w-100 border border-2 d-flex align-items-center gap-2 justify-content-center">
              <span>
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  strokeWidth="1.5"
                  stroke="currentColor"
                  className="icon-very-small"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M18 7.5v3m0 0v3m0-3h3m-3 0h-3m-2.25-4.125a3.375 3.375 0 1 1-6.75 0 3.375 3.375 0 0 1 6.75 0ZM3 19.235v-.11a6.375 6.375 0 0 1 12.75 0v.109A12.318 12.318 0 0 1 9.374 21c-2.331 0-4.512-.645-6.374-1.766Z"
                  />
                </svg>
              </span>
              <span>Dodaj</span>
            </button>
          </div>
        </div>
      </div>
      <div className="col-md-12 d-flex mt-3 gap-2">
        {members.map(member => (
          <Member name={member.name} key={member.key} />
        ))}
      </div>
    </div>
  );
}
