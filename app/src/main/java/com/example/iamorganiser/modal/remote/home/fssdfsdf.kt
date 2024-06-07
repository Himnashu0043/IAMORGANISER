package com.example.iamorganiser.modal.remote.home

data class fssdfsdf(
    var code: Int?,
    var `data`: List<Data?>?,
    var message: String?,
    var success: Boolean?
) {
    data class Data(
        var __v: Int?,
        var _id: String?,
        var adultCount: Int?,
        var bookingNumber: String?,
        var childrenCount: Int?,
        var commisionCost: Int?,
        var createdAt: String?,
        var event: Event?,
        var eventId: String?,
        var eventSlotData: List<EventSlotData?>?,
        var eventSlotId: String?,
        var paymentMethod: String?,
        var qrCode: String?,
        var subTotal: Int?,
        var taxAmount: Int?,
        var ticketAttendees: List<TicketAttendee?>?,
        var ticketNumber: String?,
        var ticketPdfLink: String?,
        var ticketStatus: String?,
        var totalAmount: Int?,
        var totalAttendees: Int?,
        var updatedAt: String?,
        var userId: String?
    ) {
        data class Event(
            var __v: Int?,
            var _id: String?,
            var attendentAgeGroup: String?,
            var availableTickets: Int?,
            var commissionStatus: String?,
            var createdAt: String?,
            var eventArtistImage: String?,
            var eventArtistName: String?,
            var eventCategory: String?,
            var eventCompletionStatus: String?,
            var eventDescription: String?,
            var eventDuration: Int?,
            var eventEndDate: String?,
            var eventGenre: String?,
            var eventImages: List<String?>?,
            var eventLanguages: List<String?>?,
            var eventLocation: EventLocation?,
            var eventNumber: String?,
            var eventOrganiserList: List<String?>?,
            var eventOrganiserStatus: String?,
            var eventStartDate: String?,
            var eventTitle: String?,
            var eventVideo: List<String?>?,
            var startingTicketPrice: Int?,
            var status: String?,
            var totalAttendees: Int?,
            var totalTicketSold: Int?,
            var totalTickets: Int?,
            var totalTicketsPerSlot: Int?,
            var updatedAt: String?,
            var whyToAttendEvent: String?
        ) {
            data class EventLocation(
                var coordinates: List<Double?>?,
                var locationName: String?,
                var type: String?
            )
        }

        data class EventSlotData(
            var __v: Int?,
            var _id: String?,
            var availableTickets: Int?,
            var bookedTickets: Int?,
            var createdAt: String?,
            var eventId: String?,
            var slotData: SlotData?,
            var slotDate: String?,
            var slotId: String?,
            var status: String?,
            var ticketTypeData: TicketTypeData?,
            var ticketTypeId: String?,
            var totalEntries: Int?,
            var totalTickets: Int?,
            var updatedAt: String?
        ) {
            data class SlotData(
                var __v: Int?,
                var _id: String?,
                var createdAt: String?,
                var endTime: String?,
                var eventId: String?,
                var startTime: String?,
                var startTimeInMinutes: Int?,
                var updatedAt: String?
            )

            data class TicketTypeData(
                var __v: Int?,
                var _id: String?,
                var adultPrice: Int?,
                var childPrice: Int?,
                var createdAt: String?,
                var eventId: String?,
                var ticketQuantity: Int?,
                var ticketType: String?,
                var updatedAt: String?
            )
        }

        data class TicketAttendee(
            var __v: Int?,
            var _id: String?,
            var age: Int?,
            var checkInBy: String?,
            var checkInTime: String?,
            var createdAt: String?,
            var email: String?,
            var isPrimary: Boolean?,
            var is_checkedIn: Boolean?,
            var name: String?,
            var phoneNumber: String?,
            var ticketId: String?,
            var updatedAt: String?,
            var userData: List<UserData?>?
        ) {
            data class UserData(
                var _id: String?,
                var firstName: String?,
                var lastName: String?,
                var phone: String?,
                var phoneCountryCode: String?,
                var professionalName: String?,
                var profileImage: String?,
                var userId: String?,
                var userRating: Int?,
                var userRatingCount: Int?,
                var userType: String?
            )
        }
    }
}