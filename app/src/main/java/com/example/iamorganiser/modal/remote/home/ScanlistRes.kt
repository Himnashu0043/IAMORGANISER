package com.example.iamorganiser.modal.remote.home

data class ScanlistRes(
    var code: Int?,
    var data: ArrayList<Data>?,
    var message: String?,
    var success: Boolean?
) {
    data class Data(
        var __v: Int?,
        var _id: String?,
        var attendentAgeGroup: String?,
        var availableTickets: Int?,
        var commissionStatus: String?,
        var createdAt: String?,
        var eventArtistImage: String?,
        var eventArtistName: String?,
        var eventCategory: String?,
        var eventDescription: String?,
        var eventDuration: Int?,
        var eventEndDate: String?,
        var eventGenre: String?,
        var eventImages: ArrayList<String>?,
        var eventLanguages: ArrayList<String>?,
        var eventLocation: EventLocation?,
        var eventNumber: String?,
        var eventOrganiserList: ArrayList<String>?,
        var eventOrganiserStatus: String?,
        var eventOrganizers: ArrayList<EventOrganizer>?,
        var eventSlots: ArrayList<EventSlot>?,
        var eventStartDate: String?,
        var eventTicketTypes: ArrayList<EventTicketType>?,
        var eventTitle: String?,
        var eventVideo: ArrayList<String?>,
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

        data class EventOrganizer(
            var _id: String?,
            var createdAt: String?,
            var email: String?,
            var firstName: String?,
            var lastName: String?,
            var phone: String?,
            var phoneCountryCode: String?,
            var profileImage: String?,
            var userId: String?,
            var walletRequired: Boolean?
        )

        data class EventSlot(
            var __v: Int?,
            var _id: String?,
            var createdAt: String?,
            var endTime: String?,
            var eventId: String?,
            var startTime: String?,
            var startTimeInMinutes: Int?,
            var updatedAt: String?
        )

        data class EventTicketType(
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
}