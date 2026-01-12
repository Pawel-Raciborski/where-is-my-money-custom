export default function CreateGroupForm() {
  return (
    <div className="card rounded-3 p-4 shadow-sm">
      <div className="row">
        <button className="btn">
          <span className="mx-2">&arr;</span>Powrót
        </button>
      </div>
      <div className="d-flex flex-column text-center mt-5">
        <h2 className="fs-4">Utwórz nową grupę</h2>
        <p className="text-secondary">Podaj nazwę i swoje imię</p>
      </div>
      <div className="d-flex flex-column mt-2">
        <div className="mt-2">
          <label className="form-label fw-bold">Nazwa grupy</label>
          <input className="form-control" placeholder="np. Wakacje w górach" />
        </div>

        <div className="mt-4">
          <label className="form-label fw-bold">Twoje imię</label>
          <input className="form-control" placeholder="np. Anna" />
        </div>

        <div className="mt-4">
          <button className="btn bg-linear-gradient w-100 text-white">
            Utwórz grupę
          </button>
        </div>
      </div>
    </div>
  );
}
