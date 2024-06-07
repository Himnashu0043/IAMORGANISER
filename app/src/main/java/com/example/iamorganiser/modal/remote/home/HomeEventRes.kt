package com.example.iamorganiser.modal.remote.home

data class HomeEventRes(
    var code: Int?,
    var data: ArrayList<Data>?,
    var message: String?,
    var success: Boolean?
) {
    data class Data(
        var paginationData: ArrayList<PaginationData>?,
        var totalCount: List<TotalCount>?
    ) {
        data class PaginationData(
            var __v: Int?,
            var _id: String?,
            var attendentAgeGroup: String?,
            var availableTickets: Int?,
            var bookedTickets: Int?,
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
            var eventSlots: ArrayList<EventSlots>?,
            var eventStartDate: String?,
            var eventTitle: String?,
            var eventVideo: ArrayList<String?>,
            var startDate: String?,
            var startTime: String?,
            var startingTicketPrice: Int?,
            var status: String?,
            var totalAttendees: Int?,
            var totalEntries: Int?,
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

            data class EventSlots(
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
            }
        }

        data class TotalCount(
            var count: Int?
        )
    }
}