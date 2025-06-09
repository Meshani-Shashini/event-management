import '../styles/Model.css'; // ✅ Regular CSS
import Button from './Button';

function Modal({ isOpen, onClose, onConfirm, message }) {
  if (!isOpen) return null;

  return (
    <div className="overlay"> {/* ✅ Use className directly */}
      <div className="modal">
        <p>{message}</p>
        <Button onClick={onConfirm}>Confirm</Button>
        <Button onClick={onClose}>Cancel</Button>
      </div>
    </div>
  );
}

export default Modal;
