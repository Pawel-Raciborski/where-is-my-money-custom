import styles from "../styles/MainPage.module.css";

export default function MainPage() {
  return (
    <div className="container flex-column">
      <div className="row justify-content-center mt-5">
        <div className="col-md-10 justify-content-center ">
          <div className="row mb-3 justify-content-center text-center">
            <div className="col-md">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke-width="1.5"
                stroke="currentColor"
                className={styles.wallet}
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  d="M21 12a2.25 2.25 0 0 0-2.25-2.25H15a3 3 0 1 1-6 0H5.25A2.25 2.25 0 0 0 3 12m18 0v6a2.25 2.25 0 0 1-2.25 2.25H5.25A2.25 2.25 0 0 1 3 18v-6m18 0V9M3 12V9m18 0a2.25 2.25 0 0 0-2.25-2.25H5.25A2.25 2.25 0 0 0 3 9m18 0V6a2.25 2.25 0 0 0-2.25-2.25H5.25A2.25 2.25 0 0 0 3 6v3"
                />
              </svg>
            </div>
            <p className="text-black mb-2 mt-4 fs-5">Where is my money?</p>
            <p className="text-secondary fs-5 mt-3">
              Proste rozliczenia grupowe bez logowania. Utwórz grupę, udostępnij
              link i zacznij dzielić wydatki.
            </p>
          </div>
          <div className="row justify-content-center mt-5">
            <div className="col-md-12 justify-content-center d-flex gap-3">
              <a
                href="#"
                className="btn bg-linear-gradient text-white px-4 py-3 fw-bold rounded-3"
              >
                Utwórz grupę &rarr;
              </a>
            </div>
          </div>
          <div className="row margin-y-medium justify-content-center text-center mt-4">
            <span className={`mb-3 ${styles.separator}`}></span>
            <span className="text-secondary fs-5">lub</span>
            <span className={`mt-3 ${styles.separator}`}></span>
          </div>

          <div className="row justify-content-center margin-y-medium">
            <div className="col-md-6">
              <div className="card border-1 shadow-sm p-3">
                <p className="card-title fs-4 d-flex align-items-center">
                  <span>
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      fill="none"
                      viewBox="0 0 24 24"
                      stroke-width="1.5"
                      stroke="currentColor"
                      className="link"
                    >
                      <path
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        d="M13.19 8.688a4.5 4.5 0 0 1 1.242 7.244l-4.5 4.5a4.5 4.5 0 0 1-6.364-6.364l1.757-1.757m13.35-.622 1.757-1.757a4.5 4.5 0 0 0-6.364-6.364l-4.5 4.5a4.5 4.5 0 0 0 1.242 7.244"
                      />
                    </svg>
                  </span>{" "}
                  <span>Dołącz do istniejącej grupy</span>
                </p>
                <p className="text-secondary fs-5 mt-3">
                  Wklej link otrzymany od organizatora grupy
                </p>
                <div className="mt-3 d-flex flex-row gap-3">
                  <input
                    type="text"
                    className="form-control"
                    placeholder="https://whereismymoney.app/..."
                  />
                  <a className="btn btn-primary">Przejdź</a>
                </div>
              </div>
            </div>
          </div>

          <div className="row row-gap-5 justify-content-between margin-bot-medium">
            <div className="col-md-4">
              <div className="card rounded-5 shadow-sm p-4">
                <div className="row">
                  <div className="col-md text-center">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      fill="none"
                      viewBox="0 0 24 24"
                      stroke-width="1.5"
                      stroke="currentColor"
                      className={`icon-small ${styles["users"]}`}
                    >
                      <path
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        d="M15 19.128a9.38 9.38 0 0 0 2.625.372 9.337 9.337 0 0 0 4.121-.952 4.125 4.125 0 0 0-7.533-2.493M15 19.128v-.003c0-1.113-.285-2.16-.786-3.07M15 19.128v.106A12.318 12.318 0 0 1 8.624 21c-2.331 0-4.512-.645-6.374-1.766l-.001-.109a6.375 6.375 0 0 1 11.964-3.07M12 6.375a3.375 3.375 0 1 1-6.75 0 3.375 3.375 0 0 1 6.75 0Zm8.25 2.25a2.625 2.625 0 1 1-5.25 0 2.625 2.625 0 0 1 5.25 0Z"
                      />
                    </svg>
                    <p className="text-black mt-3 fs-4">Bez rejestracji</p>
                    <p className="text-secondary fs-5">
                      Dostęp przez link. Żadnych haseł ani logowania.
                    </p>
                  </div>
                </div>
              </div>
            </div>
            <div className="col-md-4">
              <div className="card rounded-5 shadow-sm p-4">
                <div className="row">
                  <div className="col-md text-center">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      fill="none"
                      viewBox="0 0 24 24"
                      stroke-width="1.5"
                      stroke="currentColor"
                      className={`icon-small ${styles["arrow-trending-up"]}`}
                    >
                      <path
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        d="M2.25 18 9 11.25l4.306 4.306a11.95 11.95 0 0 1 5.814-5.518l2.74-1.22m0 0-5.94-2.281m5.94 2.28-2.28 5.941"
                      />
                    </svg>
                    <p className="text-black mt-3 fs-4">
                      Automatyczne wyliczenia
                    </p>
                    <p className="text-secondary fs-5">
                      System sam obliczy, kto komu ile jest winien.
                    </p>
                  </div>
                </div>
              </div>
            </div>
            <div className="col-md-4">
              <div className="card rounded-5 shadow-sm p-4">
                <div className="row">
                  <div className="col-md text-center">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      fill="none"
                      viewBox="0 0 24 24"
                      stroke-width="1.5"
                      stroke="currentColor"
                      className={`icon-small ${styles["wallet-small"]}`}
                    >
                      <path
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        d="M21 12a2.25 2.25 0 0 0-2.25-2.25H15a3 3 0 1 1-6 0H5.25A2.25 2.25 0 0 0 3 12m18 0v6a2.25 2.25 0 0 1-2.25 2.25H5.25A2.25 2.25 0 0 1 3 18v-6m18 0V9M3 12V9m18 0a2.25 2.25 0 0 0-2.25-2.25H5.25A2.25 2.25 0 0 0 3 9m18 0V6a2.25 2.25 0 0 0-2.25-2.25H5.25A2.25 2.25 0 0 0 3 6v3"
                      />
                    </svg>
                    <p className="text-black mt-3 fs-4">Proste rozliczenia</p>
                    <p className="text-secondary fs-5">
                      Dodawaj wydatki i śledź bilans w czasie rzeczywistym.
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
