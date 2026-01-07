import { useState } from "react";
import styles from "../styles/GroupInfo.module.css";

export default function GroupInfo() {
  const groupName = useState("Nazwa grupy2");

  return (
    <div className="row p-3 border border-1 rounded-2 mt-5 shadow-sm">
      <div className="col-md-12 mt-2 mb-2">
        <div className="row">
          <div className="col-md-10 d-flex flex-column">
            <p className="fs-3">{groupName}</p>
            <div className="mt-1 mb-2 d-flex gap-2">
              <span>1 Uczestnik</span>
              <span>0 Wydatków</span>
            </div>
            <p className="text-secondary mt-2">Opis grupy</p>
          </div>
          <div className="col-md-2">
            <button className="btn btn-outline-light text-dark w-100 border border-2 d-flex align-items-center gap-2 justify-content-center">
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
                    d="M7.217 10.907a2.25 2.25 0 1 0 0 2.186m0-2.186c.18.324.283.696.283 1.093s-.103.77-.283 1.093m0-2.186 9.566-5.314m-9.566 7.5 9.566 5.314m0 0a2.25 2.25 0 1 0 3.935 2.186 2.25 2.25 0 0 0-3.935-2.186Zm0-12.814a2.25 2.25 0 1 0 3.933-2.185 2.25 2.25 0 0 0-3.933 2.185Z"
                  />
                </svg>
              </span>
              <span>Udostępnij</span>
            </button>
          </div>
        </div>
      </div>
      <div className="d-flex gap-2 mt-3">
        <div
          className={`col-md-6 px-3 py-2 rounded-4 ${styles["expense-background-left"]}`}
        >
          <div className="d-flex gap-2 flex-column justify-content-end">
            <p className="text-secondary fs-5">Suma wydatków</p>
            <p className="m-0">0.00zł</p>
          </div>
        </div>
        <div
          className={`col-md-6 expense-background-right px-3 py-2 rounded-4 ${styles["expense-background-right"]}`}
        >
          <div className="d-flex gap-2 flex-column justify-content-end">
            <p className="text-secondary fs-5">Do rozliczenia</p>
            <p>0 transakcji</p>
          </div>
        </div>
      </div>
    </div>
  );
}
