import { useEffect, useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import Table from '../components/Table';
import Button from '../components/Button';
import Modal from '../components/Modal';
import { EventContext } from '../context/EventContext';
import '../styles/EventListPage.css';

function EventListPage() {
  const { events, setEvents } = useContext(EventContext);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [eventToDelete, setEventToDelete] = useState(null);
  const navigate = useNavigate();

  // ✅ Corrected fetch logic (removed `.content`)
  useEffect(() => {
    axios.get('http://localhost:8080/api/events')
      .then(response => setEvents(response.data)) // use response.data directly
      .catch(error => console.error('Error fetching events:', error));
  }, [setEvents]);

  const handleDelete = (id) => {
    setEventToDelete(id);
    setIsModalOpen(true);
  };

  const confirmDelete = () => {
    axios.delete(`http://localhost:8080/api/events/${eventToDelete}`)
      .then(() => {
        setEvents(events.filter(event => event.id !== eventToDelete));
        setIsModalOpen(false);
      })
      .catch(error => console.error('Error deleting event:', error));
  };

  const headers = ['Name', 'Date', 'Location', 'Actions'];

  const renderRow = (event) => (
    <tr key={event.id}>
      <td>{event.name}</td>
      <td>{event.date}</td>
      <td>{event.location}</td>
      <td>
        <Button onClick={() => navigate(`/event/${event.id}`)}>View</Button>
        <Button onClick={() => handleDelete(event.id)}>Delete</Button>
      </td>
    </tr>
  );

  return (
    <div className="container">
      <h1>Events</h1>
      <Button onClick={() => navigate('/create')}>Create Event</Button>
      <Table headers={headers} data={events || []} renderRow={renderRow} />
      <Modal
        isOpen={isModalOpen}
        onClose={() => setIsModalOpen(false)}
        onConfirm={confirmDelete}
        message="Are you sure you want to delete this event?"
      />
    </div>
  );
}

export default EventListPage;
